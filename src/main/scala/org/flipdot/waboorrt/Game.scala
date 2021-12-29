package org.flipdot.waboorrt

import com.fasterxml.jackson.annotation.JsonProperty

object Game {

  case class Me(
                 @JsonProperty("x") x: Int,
                 @JsonProperty("y") y: Int,
                 @JsonProperty("energy") energy: Int,
                 @JsonProperty("view_range") view_range: Float,
               )

  case class Meta(
                   @JsonProperty("w") width: Int,
                   @JsonProperty("h") height: Int,
                   @JsonProperty("tick") tick: Int,
                   @JsonProperty("name") name: String,
                 )

  case class Entity(
                     @JsonProperty("x") x: Int,
                     @JsonProperty("y") y: Int,
                     @JsonProperty("type") entityType: String,
                   )

  abstract sealed class Action(val _name: String) {
    @JsonProperty def name: String = _name
  }

  object Action {
    case object Noop extends Action("NOOP")

    object Walk {
      type Direction = String
      val North = "north"
      val South = "south"
      val East = "east"
      val West = "west"
    }

    case class Walk(_direction: Walk.Direction) extends Action("WALK") {
      @JsonProperty def direction: Walk.Direction = _direction
    }

    case class Throw(_x: Int, _y: Int) extends Action("THROW") {
      @JsonProperty def x: Int = _x
      @JsonProperty def y: Int = _y
    }

    case class Look(_range: Float) extends Action("LOOK") {
      @JsonProperty def range: Float = _range
    }
  }
}
