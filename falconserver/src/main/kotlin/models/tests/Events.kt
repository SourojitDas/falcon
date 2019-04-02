package models.tests

import models.events.parseJson

import org.junit.Test


class EventsTest {


    val stubbedEventsResponse = """
        {"count":20,"overflow":true,"next":"https://api.predicthq.com/v1/events/?country=US&offset=10&q=jazz","previous":null,"results":[{"relevance":6.237084,"id":"3XvBD7uefSpdSwjCxA","title":"Jazz","description":"Harlem, 1926. The city overflows with Jazz. Folks move with musicality, and speak rhythms, and in the heart of it all is Violet. Her husband\xe2\x80\x99s affair with a beautiful young woman sets off a series of violent events and unforgivable acts. Adapted from Toni Morrison\xe2\x80\x99s stunning novel and musically underscored by Bay Area jazz musician Marcus Shelby\xe2\x80\x94Jazz is a theatrical composition. Peeling back, layered accounts and alternating perspectives expose ultimately sympathetic characters, who\xe2\x80\x94like the growing New York neighborhood and the winding woods of their youth\xe2\x80\x94reveal their own rhythms.","category":"performing-arts","labels":["performing-arts"],"rank":11,"local_rank":33,"duration":0,"start":"2019-04-29T02:30:00Z","end":"2019-04-29T02:30:00Z","updated":"2019-03-22T05:45:02Z","first_seen":"2019-03-22T05:39:54Z","timezone":"America/Los_Angeles","location":[-122.535998,37.897365],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5332921","5370468","8479417"],["6295630","6255149","6252001","5332921","5370468","5392567"]],"state":"active"},{"relevance":6.0756946,"id":"dt9YTz6AsWSRQJ89pE","title":"Jazz","description":"","category":"performing-arts","labels":["performing-arts"],"rank":16,"local_rank":39,"duration":0,"start":"2019-04-28T23:00:00Z","end":"2019-04-28T23:00:00Z","updated":"2019-02-07T14:31:44Z","first_seen":"2018-09-23T04:41:05Z","timezone":"America/Los_Angeles","location":[-122.535998,37.897365],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5332921","5370468","8479417"],["6295630","6255149","6252001","5332921","5370468","5392567"]],"state":"active"},{"relevance":6.0756946,"id":"HZk7ByizFPxD7GbdKX","title":"Jazz","description":"","category":"performing-arts","labels":["performing-arts"],"rank":16,"local_rank":39,"duration":0,"start":"2019-04-28T02:30:00Z","end":"2019-04-28T02:30:00Z","updated":"2019-02-07T11:14:17Z","first_seen":"2018-09-23T04:38:56Z","timezone":"America/Los_Angeles","location":[-122.535998,37.897365],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5332921","5370468","8479417"],["6295630","6255149","6252001","5332921","5370468","5392567"]],"state":"active"},{"relevance":6.022954,"id":"qHBa44diSk8ajrFcqH","title":"Jazz","description":"","category":"performing-arts","labels":["performing-arts"],"rank":16,"local_rank":39,"duration":0,"start":"2019-04-27T02:30:00Z","end":"2019-04-27T02:30:00Z","updated":"2019-02-07T08:45:31Z","first_seen":"2018-09-23T04:20:41Z","timezone":"America/Los_Angeles","location":[-122.535998,37.897365],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5332921","5370468","8479417"],["6295630","6255149","6252001","5332921","5370468","5392567"]],"state":"active"},{"relevance":6.0077744,"id":"JAkwiNhbQyn5jLfyc2","title":"Jazz","description":"","category":"performing-arts","labels":["performing-arts"],"rank":17,"local_rank":39,"duration":0,"start":"2019-04-26T02:30:00Z","end":"2019-04-26T02:30:00Z","updated":"2019-03-22T05:50:30Z","first_seen":"2018-09-23T04:40:03Z","timezone":"America/Los_Angeles","location":[-122.535998,37.897365],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5332921","5370468","8479417"],["6295630","6255149","6252001","5332921","5370468","5392567"]],"state":"active"},{"relevance":4.270636,"id":"64un3x3PXdu75tbdiM","title":"Jazz Is PHSH and Jazz Is Phish","description":"","category":"concerts","labels":["concert","music"],"rank":44,"local_rank":66,"duration":0,"start":"2019-04-03T03:00:00Z","end":"2019-04-03T03:00:00Z","updated":"2019-03-20T03:01:26Z","first_seen":"2019-01-31T01:30:54Z","timezone":"America/Denver","location":[-105.076449,40.585265],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5417618","5578884","5577147"]],"state":"active"},{"relevance":4.2705994,"id":"yTW7Lv8Bx5LGKZtF3v","title":"LIVE JAZZ","description":"Live Jazz music performed by Kat Gang Jazz.","category":"concerts","labels":["concert","music"],"rank":0,"local_rank":0,"duration":7200,"start":"2019-04-04T01:00:00Z","end":"2019-04-04T03:00:00Z","updated":"2019-03-11T10:20:45Z","first_seen":"2019-03-11T10:20:24Z","timezone":"America/New_York","location":[-73.974355,40.76461],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5128638","5128594","7932601"],["6295630","6255149","6252001","5128638","5128594","7250946","5125771"]],"state":"active"},{"relevance":4.269382,"id":"rEEGyq4nUiHZeMvPi2","title":"LIVE JAZZ","description":"Live Jazz music performed by Kat Gang Jazz.","category":"concerts","labels":["concert","music"],"rank":0,"local_rank":0,"duration":7200,"start":"2019-04-11T01:00:00Z","end":"2019-04-11T03:00:00Z","updated":"2019-03-11T05:47:26Z","first_seen":"2019-03-11T05:46:58Z","timezone":"America/New_York","location":[-73.974355,40.76461],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5128638","5128594","7932601"],["6295630","6255149","6252001","5128638","5128594","7250946","5125771"]],"state":"active"},{"relevance":4.2468524,"id":"rknDxRC5n95omxrN6E","title":"LIVE JAZZ","description":"Live Jazz music performed by Kat Gang Jazz.","category":"concerts","labels":["concert","music"],"rank":0,"local_rank":0,"duration":7200,"start":"2019-03-21T01:00:00Z","end":"2019-03-21T03:00:00Z","updated":"2019-03-11T05:23:39Z","first_seen":"2019-03-11T05:22:57Z","timezone":"America/New_York","location":[-73.974355,40.76461],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5128638","5128594","7932601"],["6295630","6255149","6252001","5128638","5128594","7250946","5125771"]],"state":"active"},{"relevance":4.243531,"id":"xwE3RKHvWmrNeA6PeX","title":"Jazz Night","description":"Sweet Jazz with Christine Autumn","category":"concerts","labels":["concert","music"],"rank":17,"local_rank":49,"duration":0,"start":"2019-04-29T01:30:00Z","end":"2019-04-29T01:30:00Z","updated":"2019-03-12T03:39:51Z","first_seen":"2019-03-12T03:39:35Z","timezone":"America/Denver","location":[-105.574837,36.407916],"scope":"locality","country":"US","place_hierarchies":[["6295630","6255149","6252001","5481136","5493780","5493811"]],"state":"active"}]}
    """


    @Test
    fun `test pasrseJson success`() {
        val result = parseJson(stubbedEventsResponse)
        assert(result!!.count == 20)
        assert(result.overflow ?: true)
        assert(result.next == "https://api.predicthq.com/v1/events/?country=US&offset=10&q=jazz")
        assert(result.resultMembers!![0].relevance == 6.237084)
        assert(result.resultMembers!![0].id == "3XvBD7uefSpdSwjCxA")
        assert(result.resultMembers!![0].title == "Jazz")
        //assert(result!!.resultMembers!![0].description == "Harlem, 1926. The city overflows with Jazz. Folks move with musicality, and speak rhythms, and in the heart of it all is Violet. Her husband\xe2\x80\x99s affair with a beautiful young woman sets off a series of violent events and unforgivable acts. Adapted from Toni Morrison\xe2\x80\x99s stunning novel and musically underscored by Bay Area jazz musician Marcus Shelby\xe2\x80\x94Jazz is a theatrical composition. Peeling back, layered accounts and alternating perspectives expose ultimately sympathetic characters, who\xe2\x80\x94like the growing New York neighborhood and the winding woods of their youth\xe2\x80\x94reveal their own rhythms.")
        assert(result.resultMembers!![0].category == "performing-arts")
        assert(result.resultMembers!![0].labels!![0] == "performing-arts")
        assert(result.resultMembers!![0].rank == 11)
        assert(result.resultMembers!![0].localRank == 33)
        assert(result.resultMembers!![0].duration == 0)
        assert(result.resultMembers!![0].start == "2019-04-29T02:30:00Z")
        assert(result.resultMembers!![0].end == "2019-04-29T02:30:00Z")
        assert(result.resultMembers!![0].updated == "2019-03-22T05:45:02Z")
        assert(result.resultMembers!![0].firstSeen == "2019-03-22T05:39:54Z")
        assert(result.resultMembers!![0].timezone == "America/Los_Angeles")
        assert(result.resultMembers!![0].location!![0] == -122.535998)
        assert(result.resultMembers!![0].scope == "locality")
        assert(result.resultMembers!![0].country == "US")
        //assert(result!!.resultMembers!![0].placeHierarchies!![0] == "6295630")











    }


}
