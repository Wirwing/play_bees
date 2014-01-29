package models;

import java.util.Date;

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class Event(

	id: Pk[Long] = NotAssigned,
	name: String,
	date: Date,
	hour: Date,
	description: String

	)

object Event {

	val event = {
		get[Pk[Long]]("event.id") ~
		get[String]("event.name") ~
		get[Date]("event.event_date") ~
		get[Date]("event.event_hour") ~
		get[String]("event.description") map {
			case id~name~date~hour~description => Event(id, name, date, hour, description)
		}
	}

	/**
   * Retrieve all events.
   */
	def all(): List[Event] = DB.withConnection{
		implicit c => SQL("select * from event").as( event *)
	}

	/**
   * Retrieve a computer from the id.
   */
   def findById(id: Long): Option[Event] = {
   	DB.withConnection { implicit connection =>
   		SQL("select * from event where id = {id}").on('id -> id).as(Event.event.singleOpt)
   	}
   }

   /**
	 * Create a event.
	 *
	 * @param name The event name.
	 * @param date The day when the event happens.
	 * @param hour The hour of the event.
	 * @param description The description about of the event.
	 */
   def create(name: String, date: Date, hour: Date, description: String){
   	DB.withConnection { implicit c =>
   		SQL("insert into event (name, event_date, event_hour, description)  values ({name}, {event_date}, {event_hour}, {description})").
   		on(
   			'name -> name,
   			'event_date -> date,
   			'event_hour -> hour,
   			'description -> description
   			).executeUpdate()
   	}
   }

	/**
	 * Update a event.
	 *
	 * @param id The event id
	 * @param event The event values.
	 */
	 def update(id: Long, event: Event) = {
	 	DB.withConnection { implicit connection =>
	 		SQL(
	 			"""
	 			update event
	 			set name = {name}, event_date = {date}, event_hour = {hour}, description = {description}
	 			where id = {id}
	 			"""
	 			).on(
	 			'id -> id,
	 			'name -> event.name,
	 			'date -> event.date,
	 			'hour -> event.hour,
	 			'description -> event.description
			).executeUpdate()
		}
	}

	/**
	 * Delete a event.
	 *
	 * @param id The event id
	 */
	 def delete(id: Long){
	 	DB.withConnection { implicit connection =>
  			SQL("delete from event where id = {id}").on('id -> id).executeUpdate()
		} 

	 }

}

