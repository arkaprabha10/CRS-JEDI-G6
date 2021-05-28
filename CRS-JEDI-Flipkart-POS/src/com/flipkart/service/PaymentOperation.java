/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;

import com.flipkart.bean.Payment;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.dao.PaymentDaoInterface;
import com.flipkart.dao.PaymentDaoOperation;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import com.flipkart.exception.PaymentFailedException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Asus
 *
 */
public class PaymentOperation implements PaymentInterface{

	private static final Logger logger = LogManager.getLogger(PaymentOperation.class);

	@Override
	public void makePayment(Payment payment) {

		try {
			PaymentDaoInterface paymentObj = new PaymentDaoOperation();
			paymentObj.makePayment(payment);
		} catch (PaymentFailedException e) {
			logger.error(e.getMessage());
		}
	}

}
