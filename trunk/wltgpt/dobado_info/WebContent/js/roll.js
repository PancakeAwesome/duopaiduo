roll(function($) {
//c的值为每次滚动数
var slideContainer = $('#slideContainer'),
    c = 1,
    s_w = 182 * c,
    maxCounts = slideContainer.find('li').size() - 0,
    gameOver = true,
    slideCounts = 7,
    sTimer;
    console.log(maxCounts);
$('#link_prev').on('click', function() {
    clearInterval(sTimer);
    if (gameOver) {
        gameOver = false;
        slideContainer.animate({
            left: '+=' + s_w
        }, 500, function() {
            gameOver = true;
            slideContainer.animate({
                left: '-=' + s_w
            }, 0);
            var html = '';
            slideContainer.find('li:gt(' + (maxCounts - c - 1) + ')').each(function() {
                html += '<li>' + $(this).html() + '</li>';
            });
            // console.log(counts_l);
            slideContainer.find('li:gt(' + (maxCounts - c - 1) + ')').remove();
            slideContainer.html(html + slideContainer.html());
        });
    }
});
$('#link_next').on('click', function() {
    clearInterval(sTimer);
    link_next_event();
});

function link_next_event() {
    if (gameOver) {
        gameOver = false;
        slideContainer.animate({
            left: '-=' + s_w
        }, 500, function() {
            gameOver = true;
            slideContainer.animate({
                left: '+=' + s_w
            }, 0);
            // console.log(counts_r);
            slideContainer.find('li:lt(' + c + ')').clone().appendTo(slideContainer);
            slideContainer.find('li:lt(' + c + ')').remove();
        });
    }
}

lastCLiHtml();
slideContainer.find('li:gt(' + (maxCounts - 1) + ')').remove();
function lastCLiHtml() {
    var html = '';
    slideContainer.find('li:gt(' + (maxCounts - c - 1) + ')').each(function() {
        html += '<li>' + $(this).html() + '</li>';
    });
    slideContainer.html(html + slideContainer.html()).css({
        'margin-left': -s_w + 'px'
    });
}

var l_hover = false, m_hover = false, r_hover = false;
$('#links').on({
    'mouseover': function() {
        m_hover = true;
        clearInterval(sTimer);
    },
    'mouseout': function() {
        m_hover = false;
        isStartGo();
    }
});

$('#link_next, #link_prev').on('mouseout', function() {
    l_hover = false;
    r_hover = false;
    isStartGo();
})
$('#link_next, #link_prev').on('mouseover', function() {
    l_hover = true;
    r_hover = true;
    clearInterval(sTimer);
})
setInverterTimer();
function setInverterTimer() {
    clearInterval(sTimer);
    sTimer = setInterval(function() {
        link_next_event();
    }, 2000);
}

function isStartGo() {
    var st = setTimeout(function() {
        if (!l_hover && !m_hover && !r_hover) {
            setInverterTimer();
        }
    }, 1000);
}

});