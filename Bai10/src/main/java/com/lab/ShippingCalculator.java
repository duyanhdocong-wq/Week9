package com.lab;

public class ShippingCalculator {

    public double calculate(double weight, String type) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (type.equals("EXPRESS")) return weight * 5000 + 20000;
        if (type.equals("STANDARD")) return weight * 3000;
        throw new IllegalArgumentException("Unknown type: " + type);
    }

    public static void main(String[] args) {
        ShippingCalculator calculator = new ShippingCalculator();

        try {
            System.out.println("   SHIPPING CALCULATOR - BAI 10");

            double cost1 = calculator.calculate(5.0, "STANDARD");
            System.out.println(">> Don hang 5kg (STANDARD): " + cost1 + " VND");

            double cost2 = calculator.calculate(2.0, "EXPRESS");
            System.out.println(">> Don hang 2kg (EXPRESS): " + cost2 + " VND");

            System.out.println("Chuong trinh thuc thi thanh cong!");

        } catch (Exception e) {
            System.err.println("Co loi xay ra: " + e.getMessage());
        }
    }
}