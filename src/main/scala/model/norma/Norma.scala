package cl.uchile.dcc.citric
package model.norma

//import model.character.PlayerCharacter

abstract class Norma (val level: Int,
                      val stars: Int,
                      val victories: Int) extends NormaLevel {

  /**
   * Allows the player to choose between stars or victories
   * //@param playerCharacter the player that is choosing
   * @return "stars" if the player chooses stars, "victories" if the player chooses victories
   */
  //def chooseRequirement(player: PlayerCharacter): String = {
    // Logic for allowing the player to choose between stars or victories
    // Return "stars" or "victories" based on the player's choice
  //}

  //def checkProgress(player: PlayerCharacter): Boolean = {
    // Logic for checking if the player has met the chosen requirement
    // Return true if the player can advance to the next level, otherwise false
  //}

  //def normaClear(player: PlayerCharacter): Unit = {
    // Logic for advancing the player to the next Norma level
    // (if the player has met a Home panel and has met the chosen requirement)
  //}

  /* def normaCheck(player: PlayerCharacter, objective: NormaType): Unit = {
    objective match {
      case chooseStars(stars) if player.stars >= stars => player.normaClear()
      case chooseVictories(victories) if player.victories >= victories => player.normaClear()
      case _ =>
    }
  }*/


  //  var choose: Option[NormaType] = stars || victories

  /**
   *
   *
   */
  /*def completeObjective(player: PlayerCharacter): Unit = {
    choose match {
      case completeStars => player.stars = 0
      case completeVictories => player.victories = 0
    }
  }*/


  /**
   *
   *
   */
  /*def chooseNormaObjective(objective: NormaType): Unit = {
    normaLevel = objective
    (stars, victories) match {
      case (stars, victories) if
      =>
        increaseNormaLevel()
      case _ =>
    }
  }*/

  /*
   * chooseNormaObjective(objective, StarsChoice) // Completes based on stars
   chooseNormaObjective(objective, VictoriesChoice) // Completes based on victories
 */

  /*def increaseNormaLevel(): Unit = {
    normaLevel match {
      case Norma.Norma1 => chooseNormaObjective(Norma.Norma2)
      case Norma.Norma2 => chooseNormaObjective(Norma.Norma3)
      case Norma.Norma3 => chooseNormaObjective(Norma.Norma4)
      case Norma.Norma4 => chooseNormaObjective(Norma.Norma5)
      case Norma.Norma5 => chooseNormaObjective(Norma.Norma6)
      case Norma.Norma6 =>
    }
  }*/

}
