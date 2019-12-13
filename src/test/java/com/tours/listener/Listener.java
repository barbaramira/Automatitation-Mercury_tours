package com.tours.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.tours.utiles.ExtentManager;
import com.tours.utiles.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    public synchronized void onStart(ITestContext context) {
        Log.info("=========== onStart :-" + context.getName() + "===============");

    }

    public synchronized void onFinish(ITestContext context) {
        Log.info("=========== onFinish :-" + context.getName() + "===============");
        extent.flush();
    }

    public synchronized void onTestStart(ITestResult result) {

        Log.info(result.getMethod().getMethodName() + " Started");
        Log.info(result.getMethod().getDescription());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
    }

    public synchronized void onTestSuccess(ITestResult result) {

        Log.info(result.getMethod().getMethodName() + " Passed");
        test.get().pass("Test passed");
    }

    public synchronized void onTestFailure(ITestResult result) {

        Log.info("Failed because of - " + result.getThrowable());
        test.get().fail(result.getThrowable());
    }

    public synchronized void onTestSkipped(ITestResult result) {

        Log.info("Skipped because of - " + result.getThrowable());
        test.get().skip(result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }



}
