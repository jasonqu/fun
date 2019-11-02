package demo.scala.cats

import cats.Monad

import scala.util.Try

import cats._
import cats.implicits._

object Taxi extends App {
  case class Account(id: Int, valid: Boolean)
  case class Order(id: String, amount: Int)

  case class TaxiRequest(accountId: Int, position: String)
  case class TaxiResponse[A](code: Int, payload: A)

  type F[A] = Try[A]
  def getAccount(id: Int): F[Account] = ???
  def queryUnfinishedOrder(account: Account): F[Option[Order]] = ???
  def createTaxiOrder(account: Account, position: String): F[Order] = ???


  def taxiIfElse(req: TaxiRequest): F[TaxiResponse[Order]] =
    getAccount(req.accountId) flatMap { account =>
      if (!account.valid) {
        throw new RuntimeException("account invalid")
      } else {
        queryUnfinishedOrder(account) flatMap { orderOpt =>
          if (orderOpt.isDefined) {
            TaxiResponse(1, orderOpt.get).pure[F]
          } else {
            createTaxiOrder(account, req.position)
              .map(o => TaxiResponse(0, o))
          }
        }
      }
    }


  def taxi(req: TaxiRequest): F[TaxiResponse[Order]] =
    getAccount(req.accountId) flatMap {
      case account if account.valid => queryUnfinishedOrder(account) flatMap {
        case Some(order) => TaxiResponse(1, order).pure[F]
        case None => createTaxiOrder(account, req.position)
          .map(o => TaxiResponse(0, o))
      }
      case _ => throw new RuntimeException("account invalid")
    }
}
