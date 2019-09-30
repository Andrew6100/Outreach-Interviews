package com.outreach.interviews;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.outreach.interviews.map.builder.UOttawaInterviewAndrewLe;

public class TestUOttawaInterviewAndrewLe 
{	
	//We are expecting this to return the longitude and latitude for the university of ottawa
	@Test
	public void testLatAndLng1() throws UnsupportedOperationException, IOException {
		String ottawaU =new UOttawaInterviewAndrewLe.GetLatAndLng()
						.setAddress("75+Laurier+Street")
						.build();
		System.out.println("Universtiy of Ottawa " + ottawaU);
	}
	
	//We are expecting this to return the longitude and latitude for the Mcdonalds located on fallowfield road.
	@Test
	public void testLatAndLng2() throws UnsupportedOperationException, IOException {
		String mcDonalds =new UOttawaInterviewAndrewLe.GetLatAndLng()
						.setAddress("3340+Fallowfield+Road")
						.build();
		System.out.println("Mcdonalds on Fallowfield Road " + mcDonalds);
	}

	//We are expecting this to return the longitude and latitude for Wellington Street (Where parliament hill is located).
	@Test
	public void testLatAndLng3() throws UnsupportedOperationException, IOException {
		String parliament =new UOttawaInterviewAndrewLe.GetLatAndLng()
						.setAddress("Wellington+Street")
						.build();
		System.out.println("Parliament hill " + parliament);
	}

	//We are expecting this test to fail because the address does not exist. Will not return a longitude and latitude!
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void testLatAndLng4() throws UnsupportedOperationException, IOException {
		String random1 =new UOttawaInterviewAndrewLe.GetLatAndLng()
						.setAddress("2342+sdfsgg+SasdD")
						.build();
		System.out.println(random1);
	}
	
	//We are expecting this test to fail because the address does not exist. Will not return a longitude and latitude!
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void testLatAndLng5() throws UnsupportedOperationException, IOException {
		String random2 =new UOttawaInterviewAndrewLe.GetLatAndLng()
						.setAddress("342324+gsdgsGsD+Street")
						.build();
		System.out.println(random2);
	}

	//We are expecting this test to fail because the address does not exist. Will not return a longitude and latitude!
	@Test(expected = java.lang.IndexOutOfBoundsException.class)
	public void testLatAndLng6() throws UnsupportedOperationException, IOException {
		String random3 =new UOttawaInterviewAndrewLe.GetLatAndLng()
						.setAddress("132+afAAADSSS+Drive")
						.build();
		System.out.println(random3);
	}
}
