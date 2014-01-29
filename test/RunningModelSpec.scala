import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import java.util.Date;

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class RunningModelSpec extends Specification {
  
  import models._

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  
  // --
  
  "Running model" should {
    
    "return all records" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val runnings = Running.all()

        runnings must have length(4)

      }
    }

    "insert a record" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val size = Running.all.length

        Running.create("Aniversario FMAT", 100, 200.00);

        val runnings = Running.all()

        runnings must have length(size+1)

      }
    }

    "be updated if needed" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        Running.update(1, Running(name="aniversario FMAT", length=200, price=150))
        
        val Some(updatedRunning) = Running.findById(1)
        
        updatedRunning.name must equalTo("aniversario FMAT")
                
      }
    }

    "delete a record" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val size = Running.all.length

        Running.delete(1);

        val runnings = Running.all()

        runnings must have length(size-1)

      }
    }

  }
  
}