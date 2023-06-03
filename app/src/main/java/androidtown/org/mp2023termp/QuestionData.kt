package androidtown.org.mp2023termp

object QuestionData {
    fun getQuestion(): ArrayList<Question> {

        val queList: ArrayList<Question> = arrayListOf()

        val q1 = Question(
            1,
            "What does 'HTML' stand for? ",
        "HyperText Markup Language",
            "High-level Programming Language",
            "Home Tool Management Language",
            "Hyperlink Text Manipulation Language",
            1
        )

        val q2 = Question(
            1,
            "what does 'SQL' stand for? ",
            "System Query Language",
            "Simple Query Language",
            "Structured Query Language",
            "Standard Query Language",
            3
        )

        val q3 = Question(
            1,
            "What is the most basic unit of data in a computer? ",
            "Megabyte",
            "Byte",
            "Bit",
            "Gigabyte",
            3
        )

        queList.add(q1)
        queList.add(q2)
        queList.add(q3)

        return queList
    }

}