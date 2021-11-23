package com.example.environ.tasks

interface DataTaskDescription {

    fun getTitle(position: Int): String{

        var taskDescTitleVal: String = ""

        taskDescTitleVal = when (position) {
            1 -> "Save Water" //Change these values when needed
            2 -> "Do Carpooling"
            3 -> "Sign a petition"
            4 -> "Go Vegan for a week"
            5 -> "Recycle Bags"
            6 -> "Buy less plastic"
            7 -> "Make compost pit"
            8 -> "Buy fair trade products"
            9 -> "Use eco-friendly cleaning products"
            10 -> "Post on socials"
            11 -> "Recycle and reuse"
            12 -> "Take shorter shower"
            13 -> "Save energy"
            14 -> "Green your home"
            15 -> "Go paperless"
            16 -> "Reduce fashion waste"
            17 -> "Donate unused items"
            18 -> "Go Hiking"
            19 -> "Grow your own produce"
            else -> {
                "Task title not added yet."
            }
        }
        return taskDescTitleVal
    }
    fun getDescription(position: Int): String {

        var taskDescVal: String = ""

        taskDescVal = when (position) {
            1 -> "You can save water in and around your home by making a number of small changes. Try installing a water butt and making use of grey water to avoid wasting drinking water. Also fix any leaky taps."
            2 -> "Try carpooling with a friend or co-worker. Doing this is a good way to help reduce greenhouse gas emissions. It will also provide company on your way and as they say the more the merrier."
            3 -> "Find a petition for environment betterment and sign it. Also encourage your friends to sign it. You can find some petitions in 'Sign Petitions' in dashboard tab."
            4 -> "The production of meat is one of the biggest contributors to climate change and the pollution of landscapes and waterways. By going on a vegan diet, you can help to reduce the impact the meat industry has on the Earth."
            5 -> "Use cloth or jute bags when you go grocery shopping as it will help reduce plastic contamination."
            6 -> "Single-use products, particularly plastic ones, tend to end up in landfill and the ocean, causing harm to wildlife and the environment. Prevent this by choosing to use reusable and eco-friendly alternatives instead."
            7 -> "If you have leftovers, don’t throw them away. Save them for your lunch or tea the next day, or even freeze them. However, if they do go off, use them to create compost for you garden."
            8 -> "When a product has been Fairtrade certified it means it has been produced by a company committed to sustainable production. Keep an eye out for the Fairtrade mark on the packaging of products you buy."
            9 -> "Some cleaning products contain chemicals that can be harmful to the environment. By committing to using green/eco-friendly cleaning products, you can help to prevent these chemicals from having a negative impact on the Earth."
            10 -> "Make a posts or create a campaign on your preferred social media for sustainable awareness. Get your friends excited and guide them on the road of sustainability."
            11 -> "Make sure you’re recycling by putting your rubbish in the correct bins. Also, try to reuse products and items as many times as possible before binning them to reduce waste."
            12 -> "Try reducing your shower time than usual which will help reduce water wastage that goes in the drain."
            13 -> "By using less energy, you can help to reduce carbon emissions. There are a number of ways you can save energy at home, including switching off standby appliances, turning your heating down and hanging clothes to dry instead of using a drier. \n" +
                    "And HEY! look at it this way you will be saving money and Earth. Now, that's what you call being productive."
            14 -> "Plants help clean and purify the air in your home, which is good for everyone. Plus, having live greenery in your house is proven to boost your mood. If you have a black thumb, opt for easy to care for plants that don’t require tons of watering! If you have outdoor space, plant a tree or bush—this can help to offset your carbon footprint."
            15 -> "To avoid wasting paper, choose to receive letters over email and only print off documents if necessary. You could also ask shops to email you your receipt instead of printing it off."
            16 -> "The fashion industry is one of the main polluting industries in the world. To lessen the impact this industry has on the planet, you could buy more of your clothes from charity shops and sustainable clothing outlets."
            17 -> "If you don’t use a product or wear a piece of clothing anymore, give it to a charity shop or someone you know would make use of it to help reduce waste."
            18 -> "Getting into nature is easy with nothing more than your refillable water bottle and a good pair of shoes. So, go for a hike and take advantage of the forest canopy, rippling water and scenery that nature provides while getting in your steps."
            19 -> "By growing your own fruit and vegetables, you can ensure you’re not using pesticides that will contribute to water and air pollution. This will also help to reduce the amount fossil fuels used to transport produce to supermarkets."
            else -> {
                "Task description not added yet"
            }
        }
        return taskDescVal

    }
    fun getPoints(position: Int): Int{

        var taskPointVal: Int = 0

        taskPointVal = when (position) {
            1 -> 5
            2 -> 6
            3 -> 5
            4 -> 10
            5 -> 5
            6 -> 7
            7 -> 20
            8 -> 20
            9 -> 15
            10 -> 3
            11 -> 5
            12 -> 3
            13 -> 5
            14 -> 10
            15 -> 5
            16 -> 15
            17 -> 10
            18 -> 10
            19 -> 25
            else -> {
                0
            }
        }
        return taskPointVal
    }

    fun getTitleList(): List<String> {
        return listOf(
            "Save Water",
            "Do Carpooling",
            "Sign a petition",
            "Go Vegan for a week",
            "Recycle Bags",
            "Buy less plastic",
            "Make compost pit",
            "Buy fair trade products",
            "Use eco-friendly cleaning products",
            "Post on socials",
            "Recycle and reuse",
            "Take shorter shower",
            "Save energy",
            "Green your home",
            "Go paperless",
            "Reduce fashion waste",
            "Donate unused items",
            "Go Hiking",
            "Grow your own produce"
        )
    }

    fun checkLevel(pointsSoFar: Int): Int {

        var level:Int = 0

        level = when {
            pointsSoFar<10 -> 1
            pointsSoFar in 10..19 -> 2
            pointsSoFar in 20..49 -> 3
            pointsSoFar in 50..74 -> 4
            pointsSoFar in 75..99 -> 5
            pointsSoFar in 100..149 -> 6
            pointsSoFar in 150..199 -> 7
            else -> ((pointsSoFar/100)%100).toInt() + 7
        }

        return level
    }
}
