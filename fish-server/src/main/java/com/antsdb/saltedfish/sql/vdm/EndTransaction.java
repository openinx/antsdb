/*-------------------------------------------------------------------------------------------------
 _______ __   _ _______ _______ ______  ______
 |_____| | \  |    |    |______ |     \ |_____]
 |     | |  \_|    |    ______| |_____/ |_____]

 Copyright (c) 2016, antsdb.com and/or its affiliates. All rights reserved. *-xguo0<@

 This program is free software: you can redistribute it and/or modify it under the terms of the
 GNU GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

 You should have received a copy of the GNU Affero General Public License along with this program.
 If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html>
-------------------------------------------------------------------------------------------------*/
package com.antsdb.saltedfish.sql.vdm;

import java.util.Collections;
import java.util.List;

import com.antsdb.saltedfish.sql.meta.TableMeta;

public class EndTransaction extends Statement {
    Instruction before;
    
    public EndTransaction() {
    }
    
    public EndTransaction(Instruction before) {
        super();
        this.before = before;
    }

    @Override
    public Object run(VdmContext ctx, Parameters params) {
        Object result = null;
        
        if (this.before != null) {
            result = this.before.run(ctx, params, 0);
        }
        
        ctx.session.commit();
        return result;
    }

    @Override
    public List<TableMeta> getDependents() {
        return Collections.emptyList();
    }

}
