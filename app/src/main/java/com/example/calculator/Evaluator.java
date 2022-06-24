package com.example.calculator;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Evaluator {

    private ScriptEngine engine;

    public Evaluator() {
        engine = new ScriptEngineManager().getEngineByName("rhino");
    }

    public Evaluator(String name) {
        engine = new ScriptEngineManager().getEngineByName(name);
    }

    public String evaluate(String expression) throws ScriptException {
        BigDecimal decimal = new BigDecimal(engine.eval(expression).toString());
        String result = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        return result;
    }




}