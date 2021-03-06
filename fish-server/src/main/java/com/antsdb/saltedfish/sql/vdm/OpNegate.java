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


import com.antsdb.saltedfish.cpp.FishNumber;
import com.antsdb.saltedfish.cpp.FishTimestamp;
import com.antsdb.saltedfish.cpp.Heap;
import com.antsdb.saltedfish.cpp.Value;
import com.antsdb.saltedfish.sql.DataType;

public class OpNegate extends UnaryOperator {

    public OpNegate(Operator child) {
        super(child);
    }

    @Override
    public long eval(VdmContext ctx, Heap heap, Parameters params, long pRecord) {
        long pValue = upstream.eval(ctx, heap, params, pRecord);
        if (pValue == 0) {
            return 0;
        }
        int type = Value.getType(heap, pValue);
        if (type == Value.TYPE_NUMBER) {
            pValue = FishNumber.negate(heap, pValue);
        }
        else if (type == Value.TYPE_TIMESTAMP) {
            long epoch = FishTimestamp.getEpochMillisecond(heap, pValue);
            epoch = - epoch;
            return FishTimestamp.allocSet(heap, epoch);
        }
        else {
            pValue = AutoCaster.toNumber(heap, pValue);
            pValue = FishNumber.negate(heap, pValue);
        }
        return pValue;
    }

    @Override
    public DataType getReturnType() {
        return this.upstream.getReturnType();
    }

}
