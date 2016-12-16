package com.actors

import akka.actor.Actor
import commands._

class UserActor extends Actor {
  def receive: Actor.Receive = {
    case y: CreateUserCommand => println("created")
    case _ => println("default")
  }
}