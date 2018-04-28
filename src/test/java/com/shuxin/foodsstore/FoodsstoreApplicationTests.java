package com.shuxin.foodsstore;

import com.shuxin.foodsstore.commons.enums.UserTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodsstoreApplicationTests {

	@Test
	public void contextLoads() {

		for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
			System.out.println(userTypeEnum.getCode());

		}

		System.out.println("-------------------------------------------------------------");
		List<Integer> list = Arrays.stream(UserTypeEnum.values()).map(e -> e.getCode()).collect(Collectors.toList());


		for (Integer integer : list) {
			System.out.println(integer);

		}
	}

	public static void main(String[] args) {



	}

}
