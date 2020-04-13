package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    static int cupsQuantity = 0;  // coffee cups cupsQuantity
    static final double COFFEE_PRICE = 5.0;  // COFFEE_PRICE of 1 cup of coffee
    static String orderMessage = "";  // order message
    static String totalPrice = "";  // total price for coffee

    /**
     * This method is called when the widget is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display();  // display cups quantity again
    }

    /**
     * Decrement number of cups by 1.
     */
    public void decrement(View view) {
        cupsQuantity--;  // decrease by 1
        orderMessage = "";  // set empty order message
        display();
    }

    /**
     * Increment number of cups by 1.
     */
    public void increment(View view) {
        cupsQuantity++;  // increase by 1
        orderMessage = "";  // set empty order message
        display();
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @param view - view of the main screen
     */
    public void submitOrder(View view) {
        orderMessage = getOrderMessage();
        cupsQuantity = 0;
        display();
    }

    /**
     * Creates the message output for the order.
     *
     * @return the message
     */
    private String getOrderMessage() {
        String cups = " cup ";
        if (cupsQuantity > 1) {
            cups = " cups ";
        }
        return "Name: FooBar167" +
               "\nQuantity: " + cupsQuantity + cups + "of coffee" +
               "\nTotal price: " + totalPrice +
               "\nThank you!";  // set order message
    }

    /**
     * This method is called when the cancel button is clicked.
     *
     * @param view - view of the main screen
     */
    public void cancelOrder(View view) {
        cupsQuantity = 0;
        orderMessage = "";
        display();
    }

    /**
     * This method displays the given cups quantity value on the screen.
     */
    private void display() {
        Button order = findViewById(R.id.button_order);  // order button
        Button decrease = findViewById(R.id.button_decrease);  // decrease button
        Button cancel = findViewById(R.id.button_cancel);  // cancel button
        if (cupsQuantity <= 0) {  // order only positive cups of coffee
            cupsQuantity = 0;  // cups quantity can be >= 0
            order.setEnabled(false);  // disable button
            decrease.setEnabled(false);  // disable button
            cancel.setEnabled(false);  // disable button
            order.setTextColor(getApplication().getResources().getColor(
                    android.R.color.darker_gray));  // change button color
            decrease.setTextColor(getApplication().getResources().getColor(
                    android.R.color.darker_gray));  // change button color
            cancel.setTextColor(getApplication().getResources().getColor(
                    android.R.color.darker_gray));  // change button color
        } else {  // cupsQuantity > 0
            order.setEnabled(true);  // enable button
            decrease.setEnabled(true);  // enable button
            cancel.setEnabled(true);  // enable button
            // Return old button themes
            order.setTextAppearance(getApplicationContext(), R.style.ButtonTheme);
            decrease.setTextAppearance(getApplicationContext(), R.style.ButtonSquare);
            cancel.setTextAppearance(getApplicationContext(), R.style.ButtonTheme);
        }
        // Update quantity number text view
        TextView quantityTextView = findViewById(R.id.text_quantity_number);
        quantityTextView.setText(String.format(Locale.getDefault(), "%d", cupsQuantity));
        // Display price automatically after quantity of cups is changed
        TextView priceTextView = findViewById(R.id.text_price_number);
        totalPrice = calculatePrice(cupsQuantity);
        priceTextView.setText(totalPrice);
        // Set order message
        TextView orderTextView = findViewById(R.id.text_order_message);
        orderTextView.setText(orderMessage);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private String calculatePrice(int quantity) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);  // set dollars currency
        return nf.format(cupsQuantity * COFFEE_PRICE);
    }
}
