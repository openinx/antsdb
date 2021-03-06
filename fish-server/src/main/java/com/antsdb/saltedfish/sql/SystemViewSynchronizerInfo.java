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
package com.antsdb.saltedfish.sql;

import java.util.Map;

import com.antsdb.saltedfish.nosql.Synchronizer;
import com.antsdb.saltedfish.sql.vdm.VdmContext;

/**
 * 
 * @author *-xguo0<@
 */
public class SystemViewSynchronizerInfo extends PropertyBasedView {
    @Override
    public Map<String, Object> getProperties(VdmContext ctx) {
        Synchronizer synchronizer = ctx.getOrca().getHumpback().getSynchronizer();
        return synchronizer!=null ? synchronizer.getSummary() : null;
    }

}
