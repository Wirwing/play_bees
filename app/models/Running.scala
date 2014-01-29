package models;

import java.util.Date;

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class Running(

	id: Pk[Long] = NotAssigned,
	name: String,
	length: Int,
	price: Double
	)

object Running {

	val running = {
		get[Pk[Long]]("running.id") ~
		get[String]("running.name") ~
		get[Int]("running.length") ~
		get[Double]("running.price") map {
			case id~name~length~price => Running(id, name, length, price)
		}
	}

	/**
   * Retrieve all runnings.
   */
	def all(): List[Running] = DB.withConnection{
		implicit c => SQL("select * from running").as( running *)
	}

	/**
   * Retrieve a computer from the id.
   */
   def findById(id: Long): Option[Running] = {
   	DB.withConnection { implicit connection =>
   		SQL("select * from running where id = {id}").on('id -> id).as(Running.running.singleOpt)
   	}
   }

   /**
	 * Create a running.
	 *
	 * @param name The running name.
	 * @param length The lenght of the running in meters.
	 * @param price The price of the event.
	 */
   def create(name: String, length: Int, price: Double){
   	DB.withConnection { implicit c =>
   		SQL("insert into running (name, length, price)  values ({name}, {length}, {price})").
   		on(
   			'name -> name,
   			'length -> length,
   			'price -> price
   			).executeUpdate()
   	}
   }

	/**
	 * Update a running.
	 *
	 * @param id The running id
	 * @param event The event values.
	 */
	 def update(id: Long, running: Running) = {
	 	DB.withConnection { implicit connection =>
	 		SQL(
	 			"""
	 			update running
	 			set name = {name}, length = {length}, price = {price}
	 			where id = {id}
	 			"""
	 			).on(
	 			'id -> id,
	 			'name -> running.name,
	 			'length -> running.length,
	 			'price -> running.price
			).executeUpdate()
		}
	}

	/**
	 * Delete a running.
	 *
	 * @param id The running id
	 */
	 def delete(id: Long){
	 	DB.withConnection { implicit connection =>
  			SQL("delete from running where id = {id}").on('id -> id).executeUpdate()
		} 

	 }

}