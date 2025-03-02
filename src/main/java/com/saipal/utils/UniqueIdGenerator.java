package com.saipal.utils;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UniqueIdGenerator {

	public static long generateUniqueId() {
		Random random = new Random();

		// Generate A and C (random values between 1 and 10000)
		int A = random.nextInt(10000) + 1;
		int C = random.nextInt(10000) + 1;

		// Generate B from UUID
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		BigInteger B = new BigInteger(uuid, 16).abs();

		// Generate random index (0-9)
		int ind = random.nextInt(10);

		// Permute A, B, and C based on the index
		String ret;
		if (ind == 0 || ind == 6) { // ABC
			ret = String.valueOf(A) + B + C;
		} else if (ind == 1 || ind == 7) { // ACB
			ret = String.valueOf(A) + C + B;
		} else if (ind == 2) { // BAC
			ret = B + String.valueOf(A) + C;
		} else if (ind == 3) { // BCA
			ret = B + String.valueOf(C) + A;
		} else if (ind == 4 || ind == 8) { // CAB
			ret = String.valueOf(C) + A + B;
		} else { // CBA
			ret = String.valueOf(C) + B + A;
		}

		// Convert to long and return
		return Long.parseLong(ret);
	}
}
