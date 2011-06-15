/*
 * Copyright 2011 JBoss, a divison Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.ioc.rebind.ioc.codegen.builder.control;

import org.jboss.errai.ioc.rebind.ioc.codegen.BlockStatement;
import org.jboss.errai.ioc.rebind.ioc.codegen.BooleanExpression;
import org.jboss.errai.ioc.rebind.ioc.codegen.Context;
import org.jboss.errai.ioc.rebind.ioc.codegen.Statement;

/**
 * @author Mike Brock <cbrock@redhat.com>
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public class ForLoop extends AbstractBlockConditional {
  private Statement initializer;
  private String initializerExpr;
  private Statement afterBlock;

  public ForLoop(BooleanExpression condition, BlockStatement block) {
    super(condition, block);
  }

  public ForLoop(BooleanExpression condition, BlockStatement block, Statement initializer, Statement afterBlock) {
    super(condition, block);
    this.initializer = initializer;
    this.afterBlock = afterBlock;
  }

  public ForLoop(BooleanExpression condition, BlockStatement block, String initializerExpr, Statement afterBlock) {
    super(condition, block);
    this.initializerExpr = initializerExpr;
    this.afterBlock = afterBlock;
  }

  public String generate(Context context) {
    StringBuilder builder = new StringBuilder("for (");

    if (initializerExpr != null) {
      builder.append(initializerExpr);
    }
    else if (initializer != null) {
      builder.append(initializer.generate(context));
    }
    if (!builder.toString().endsWith(";"))
      builder.append(";");
    
    builder.append(" ").append(getCondition().generate(context)).append("; ");

    if (afterBlock != null) {
      builder.append(afterBlock.generate(context));
    }

    builder.append(") {\n")
        .append(getBlock().generate(context))
        .append("\n}\n");

    return builder.toString();
  }
}
