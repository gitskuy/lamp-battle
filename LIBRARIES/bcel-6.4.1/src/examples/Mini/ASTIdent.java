/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/* Generated By:JJTree: Do not edit this line. ASTIdent.java */
/* JJT: 0.3pre1 */

package Mini;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.PUSH;

/**
 *
 */
public class ASTIdent extends ASTExpr implements org.apache.bcel.Constants {
  private String   name;
  private Variable reference; // Reference in environment to decl of this ident

  // Generated methods
  ASTIdent(final int id) {
    super(id);
  }

  ASTIdent(final MiniParser p, final int id) {
    super(p, id);
  }

  public static Node jjtCreate(final MiniParser p, final int id) {
    return new ASTIdent(p, id);
  }

  public ASTIdent(final String name, final int type, final int line, final int column) {
    super(line, column, JJTIDENT);

    this.name  = name;
    this.type  = type;
  }

  // closeNode, dump inherited

  /**
   * @return identifier and line/column number of appearance
   */
  @Override
  public String toString() {
    return super.toString() + " = " + name;
  }

  /**
   * Overrides ASTExpr.traverse()
   */
  @Override
  public ASTExpr traverse(final Environment env) {
    final EnvEntry entry = env.get(name);

    if(entry == null) {
        MiniC.addError(line, column, "Undeclared identifier " + name);
    } else if(entry instanceof Function) {
        MiniC.addError(line, column,
                     "Function " + name + " used as an identifier.");
    } else {
        reference = (Variable)entry;
    }

    return this; // Nothing to reduce/traverse further here
  }

  /**
   * Overrides AstExpr.eval()
   */
  @Override
  public int eval(final int expected) {
    final ASTIdent ident = reference.getName();
    final int      t     = ident.getType();

    is_simple = true; // (Very) simple expression, always true

    if((t == T_UNKNOWN) && (expected == T_UNKNOWN)) {
        type = T_UNKNOWN;
    } else if((t == T_UNKNOWN) && (expected != T_UNKNOWN)) {
      ident.setType(expected);
      type = expected;
    }
    else if((t != T_UNKNOWN) && (expected == T_UNKNOWN)) {
      ident.setType(t);
      type = t;
    } else {
        type = t; // Caller has to check for an error, i.e. t != expected
    }

    return type;
  }

  /**
   * Fourth pass, produce Java code.
   */
  @Override
  public void code(final StringBuffer buf) {
    if(name.equals("TRUE")) {
        ASTFunDecl.push(buf, "1");
    } else if(name.equals("FALSE")) {
        ASTFunDecl.push(buf, "0");
    } else {
        ASTFunDecl.push(buf, name);
    }
  }

  /**
   * Fifth pass, produce Java byte code.
   */
  @Override
  public void byte_code(final InstructionList il, final MethodGen method, final ConstantPoolGen cp) {
    if(name.equals("TRUE")) {
        il.append(new PUSH(cp, 1));
    } else if(name.equals("FALSE")) {
        il.append(new PUSH(cp, 0));
    } else {
      final LocalVariableGen local_var = reference.getLocalVariable();
      il.append(new ILOAD(local_var.getIndex()));
    }
    ASTFunDecl.push();
  }


  public void   setName(final String name) { this.name = name; }
  public String getName()            { return name; }
}
