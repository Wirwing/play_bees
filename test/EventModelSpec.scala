import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import java.util.Date;

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class ModelSpec extends Specification {
  
  import models._

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  
  // --
  
  "Event model" should {
    
    "return all records" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val events = Event.all()

        events must have length(4)

      }
    }

    "insert a record" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val size = Event.all.length

        Event.create("Primerizo", new Date(), new Date(), "Descriptionn");

        val events = Event.all()

        events must have length(size+1)

      }
    }

    "be updated if needed" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        Event.update(3, Event(name="Dia de la Marmota", date=new Date(), hour=new Date(), description = "dasdadasdasd"))
        
        val Some(updatedEvent) = Event.findById(3)
        
        updatedEvent.name must equalTo("Dia de la Marmota")
                
      }
    }

    "delete a record" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val size = Event.all.length

        Event.delete(1);

        val events = Event.all()

        events must have length(size-1)

      }
    }

  }
  
}