package com.github.jlgrock.snp.core.domain;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class EncounterTest {
    /**
     * public function returns void
     */
	@Test
	public void test() {
				
	    Long ln1 = new Long(201521);		
		Long pt1 = new Long(4334l);
        LocalDate dt1 = LocalDate.of(2015, 2, 2);
		Integer it1 = 201523;
	    String st1 = "Sprained Ankle";
	    List lt1 = Mockito.mock (List.class);	
	    
	    Long ln2 = new Long(201524);		
		Long pt2 = new Long(2425l);
        LocalDate dt2 = LocalDate.of(2014, 2, 4);
		Integer it2 = 201526;
	    String st2 = "Sprained Wrist";
	    List lt2 = Mockito.mock (List.class);	
	    
	    Encounter en1 = new Encounter();
	    
        en1.setDate(dt1);
	    en1.setReasonForVisit(st1);
	    en1.setObservations(lt1);
	    en1.setType(it1);
	    en1.setId(ln1);
        en1.setPatientId(pt1);

	    Encounter en2 = new Encounter();
	    
        en2.setDate(dt1);
	    en2.setReasonForVisit(st1);
	    en2.setObservations(lt1);
	    en2.setType(it1);
	    en2.setId(ln1);
        en2.setPatientId(pt1);
        
		assertTrue(en2.equals(en1));
        
		assertEquals(dt1, en1.getDate());
		assertEquals(st1, en1.getReasonForVisit());
		assertEquals(lt1, en1.getObservations());
		assertEquals(it1, en1.getType());
		assertEquals(ln1, en1.getId());
		assertEquals(pt1, en1.getPatientId());
		
        en2.setDate(dt2);
	    en2.setReasonForVisit(st2);
	    en2.setObservations(lt2);
	    en2.setType(it2);
	    en2.setId(ln2);
        en2.setPatientId(pt2);
		
		assertFalse(en2.equals(pt1));
		
		assertNotEquals(en1.getDate(), en2.getDate());
		assertNotEquals(en1.getReasonForVisit(), en2.getReasonForVisit());
		assertNotEquals(en1.getObservations(), en2.getObservations());
		assertNotEquals(en1.getType(), en2.getType());
		assertNotEquals(en1.getId(), en2.getId());
		assertNotEquals(en1.getPatientId(), en2.getPatientId());

        // has an extra hashcode at the end because of the mocking of the list
		assertEquals(true, en1.toString().contains("Encounter{id=201521, patientId=4334, date=2015-02-02, type=201523, reasonForVisit=Sprained Ankle, observations="));
		assertNotEquals(en1.hashCode(), en2.hashCode());

	}

}

