package com.example.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.script.ScriptException;

public class EvaluatorTest {

    @Test
    public void evaluate_addition() throws ScriptException {
        Evaluator evaluator = new Evaluator();
        double value = Double.valueOf(evaluator.evaluate("2+2"));
        double expected = 4.0;
        assertEquals(expected, value, 0.001);
    }

    @Test
    public void evaluate_sub() throws ScriptException {
        Evaluator evaluator = new Evaluator();
        double value = Double.valueOf(evaluator.evaluate("2-2"));
        double expected = 0.0;
        assertEquals(expected, value, 0.001);
    }

    @Test
    public void evaluate_mult() throws ScriptException {
        Evaluator evaluator = new Evaluator();
        double value = Double.valueOf(evaluator.evaluate("2*3"));
        double expected = 6.0;
        assertEquals(expected, value, 0.001);
    }

    @Test
    public void evaluate_div() throws ScriptException {
        Evaluator evaluator = new Evaluator();
        double value = Double.valueOf(evaluator.evaluate("6/3"));
        double expected = 2.0;
        assertEquals(expected, value, 0.001);
    }

    @Test
    public void evaluate_log() throws ScriptException {
        Evaluator evaluator = new Evaluator();
        double value = Double.valueOf(evaluator.evaluate("Math.log10(10)"));
        double expected = 1;
        assertEquals(expected, value, 0.001);
    }
}