class Bowling {

  public static void main(String[] args) {
    checkScore("x x x x x x x x x x x x", 300);
    checkScore("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", 90);
    checkScore("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5", 150);
    checkScore("-7 63 72 13 42 27 1- 7/ 9/ xx9", 113);
    checkScore("x 52 x 7/ 81 8/ -8 x 6/ 6/1", 136);
  }

  private static void checkScore(String rolls, int expectedScore) {
    int score = calcScore(rolls);
    System.out.println(String.format("%-32s ->  %2$4d", rolls, score));
    if (score != expectedScore) {
      System.out.println(String.format("Wrong score: expectedScore %d", expectedScore));
      System.exit(1);
    }
  }

  public static int calcScore(String rollsAsString) {
    char[] rolls = rollsAsString.replaceAll("\\s+", "").replace("-", "0").toCharArray();
    int sum = 0;
    int i = 0;
    for (int currentFrame = 0; currentFrame < 10; currentFrame++) {

      if (isStrike(rolls[i])) {
        sum += countPins(rolls[i]);
        sum += countPins(rolls[i + 1], rolls[i + 2]);
        i++;
      } else if (isSpare(rolls[i + 1])) {
        sum += countPins(rolls[i], rolls[i + 1]);
        sum += countPins(rolls[i + 2]);
        i += 2;
      } else {
        sum += countPins(rolls[i], rolls[i + 1]);
        i += 2;
      }
    }
    return sum;
  }

  private static boolean isStrike(char roll) {
    return roll == 'x';
  }

  private static boolean isSpare(char secondRoll) {
    return secondRoll == '/';
  }

  private static int countPins(char firstRoll, char secondRoll) {
    if (isSpare(secondRoll)) {
      return 10;
    } else {
      return countPins(firstRoll) + countPins(secondRoll);
    }
  }

  private static int countPins(char roll) {
    if (isStrike(roll)) {
      return 10;
    } else {
      return Character.getNumericValue(roll);
    }
  }
}
