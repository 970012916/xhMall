$(document).ready(function () {
    showAllMenu();
});
function showAllMenu() {
    debugger
    var url = "/showData";
    var data;
    var html = "";
    Base.ajax("POST", url, null,null, function (result) {
        console.log(result);
        if (result.status == "SUCCESS") {
            if (result.data != null && result.data != undefined) {
                data = result.data;
                for (var i = 0; i < data.length; i++) {
                    html += '<li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">'
                        + '  <a href="#" class="m-menu__link m-menu__toggle">'
                        + '      <i class="' + data[i].iconPath + '"></i>'
                        + '      <span class="m-menu__link-text">'
                        + data[i].menuName
                        + '      </span>'
                        + '      <i class="m-menu__ver-arrow la la-angle-right"></i>'
                        + '  </a>'
                        + '  <div class="m-menu__submenu">'
                        + '      <span class="m-menu__arrow"></span>'
                        + '      <ul class="m-menu__subnav" >'
                        + '          <li class="m-menu__item  m-menu__item--parent" aria-haspopup="true" >'
                        + '              <a href="#" class="m-menu__link ">'
                        + '                  <span class="m-menu__link-text">'
                        + data[i].menuName
                        + '                  </span>'
                        + '              </a>'
                        + '          </li>'
                    for (var j = 0; j < data[i].children.length; j++) {
                        html += '      <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover" >'
                            + '          <a href="#" class="m-menu__link m-menu__toggle">'
                            + '              <i class="m-menu__link-bullet m-menu__link-bullet--dot">'
                            + '                  <span></span>'
                            + '              </i>'
                            + '              <span class="m-menu__link-text">'
                            + data[i].children[j].menuName
                            + '              </span>'
                            + '              <i class="m-menu__ver-arrow la la-angle-right"></i>'
                            + '          </a>'
                            + '          <div class="m-menu__submenu">'
                            + '              <span class="m-menu__arrow"></span>'
                            + '              <ul class="m-menu__subnav" >'
                        for (var k = 0; k < data[i].children[j].children.length; k++) {
                            html += '                  <li class="m-menu__item " aria-haspopup="true">'
                                + '                      <a target="_blank" class="m-menu__link" onclick="menuClick()" ">'
                                + '                          <i class="m-menu__link-bullet m-menu__link-bullet--dot">'
                                + '                              <span></span>'
                                + '                          </i>'
                                + '                          <span class="m-menu__link-text">'
                                + data[i].children[j].children[k].menuName
                                + '                          </span>'
                                + '                      </a>'
                                + '                  </li>'
                        }
                        html += '              </ul>'
                            + '          </div>'
                            + '        </li>'
                    }
                    html += '      </ul>'
                        + '  </div>'
                        + '</li>'
                }
            }
        } else {
            return ""
        }
        $('#m_ver_menu').html(html);
    });
}
function menuClick() {
    Base.ajax("POST", "/test", null,"html", function (result) {
        debugger;
        console.log(result);
        $("#refresh_html").html(result);
    });
}