package androidtown.org.mp2023termp

object QuestionData {
    fun getQuestion(): ArrayList<Question> {

        val queList: ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "1 + 1? ",
        "1",
            "2",
            "3",
            "4",
            2
        )

        val q2 = Question(
            1,
            "2 + 2? ",
            "2",
            "3",
            "4",
            "5",
            3
        )

        val q3 = Question(
            1,
            "3 + 3? ",
            "2",
            "4",
            "6",
            "8",
            3
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }

}