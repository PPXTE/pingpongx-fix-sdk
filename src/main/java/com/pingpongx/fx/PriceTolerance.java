package com.pingpongx.fx;


import quickfix.DoubleField;

/**
 * @description: PriceTolerance价格滑点
 * @author: limw
 * @create: 2021-04-08 13:55
 **/
public class PriceTolerance extends DoubleField {

    static final long serialVersionUID = -2005061700001L;

    public PriceTolerance() {
        super(9001);
    }

    public PriceTolerance(double data) {
        super(9001, data);
    }
}
