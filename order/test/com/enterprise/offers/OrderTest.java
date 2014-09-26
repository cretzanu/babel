package com.enterprise.offers;


public class OrderTest {

//	SaveOffer saveOfferWorker;
//	ReadOffer readOfferWorker;
//
//	@Before
//	public void init() throws NamingException {
//		readOfferWorker = (ReadOffer) new InitialContext()
//				.lookup("offer-ear/ReadOfferEJB/remote");
//		saveOfferWorker = (SaveOffer) new InitialContext()
//				.lookup("offer-ear/SaveOfferEJB/remote");
//
//	}
//
//	@Test
//	public void test1() {
//		Offer offer1 = new Offer();
//		offer1.setOfferDate(new Date());
//		offer1.setContactName("test" + Calendar.getInstance().getTimeInMillis());
//		OfferDetail d1;
//		for (int i = 0; i < 5; i++) {
//			d1 = new OfferDetail();
//			d1.setItem("ItemTest" + Math.round((Math.random() * i)));
//			d1.setStandardPrice(Math.round((Math.random() * 300*i)));
//			d1.setOfferPrice(0.15);
//			offer1.addOfferDetail(d1);
//		}
//
//		offer1=saveOfferWorker.saveOffer(offer1);
//		assertNotNull("must have an ID now!!!",offer1.getId());
//		
//		offer1=readOfferWorker.readOffer(offer1.getId());
//		assertEquals((long)offer1.getOfferDetails().size(),5L);
//		OfferDetail d=offer1.getOfferDetails().iterator().next();
//		offer1.getOfferDetails().remove(d);
//		offer1=saveOfferWorker.saveOffer(offer1);
//		offer1=readOfferWorker.readOffer(offer1.getId());
//		assertEquals((long)offer1.getOfferDetails().size(),4L);
//	}

}
