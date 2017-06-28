/**
 * Created by cpt_crunch on 6/27/17.
 */
(function($){

    var url = '/posts.json';
    options = {
        "data": {
            title: "title",
            body: "body",
            username: "username"
        }
    }

    function writePosts(data) {
        data.forEach(function (el) {
           var html =
           '<div><h3>' + el.title +
           '</h3><p>' + el.body +
           '</p><p>' + el.owner.username +
           '</p></div>'

            $('#posts').append(html);
        });
    }

    $.ajax(url).done(function(data) {
        writePosts(data);
    });

})(jQuery);