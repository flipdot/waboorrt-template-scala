package org.flipdot.waboorrt

import com.github.arteam.simplejsonrpc.core.annotation.{JsonRpcMethod, JsonRpcParam, JsonRpcService}
import org.flipdot.waboorrt.Game.Action
import org.flipdot.waboorrt.Game.Action.Walk._
import org.flipdot.waboorrt.Game.Action._

import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.util.Random

@JsonRpcService
object Bot {

  @JsonRpcMethod("health")
  def health(): Boolean = true

  @JsonRpcMethod("next_action")
  def nextAction(
                  @JsonRpcParam("me") me: Game.Me,
                  @JsonRpcParam("meta") meta: Game.Meta,
                  @JsonRpcParam("entities") jList: java.util.List[Game.Entity]
                ): Any = {
    val entities = jList.asScala.toSeq
    (Random shuffle Set[Action](
      (Random shuffle Set[Action](
        Noop, Walk(North), Walk(South), Walk(East), Walk(West),
      )).head,
      Throw(Random.nextInt(meta.width), Random.nextInt(meta.height)),
      Look(Random.nextInt(math.max(meta.width, meta.height))),
    )).head
  }
}
