<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    function dormManagerDelete(dormManagerId) {
        if (confirm("您确定要删除这个宿管吗？")) {
            window.location = "/dormManagerDel?dormManId="+dormManagerId;
        }
    }

    $(document).ready(function () {
        $("ul li:eq(1)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title">
        宿舍管理员管理
    </div>
    <form name="myForm" class="form-search" method="post" action="/dormManager?site=dormManager">
        <button class="btn btn-success" type="button" style="margin-right: 50px;"
                onclick="javascript:window.location='/dormManSavePage?site=dormManagerSave'">添加
        </button>
        <span class="data_search">
					<select id="searchType" name="searchType" style="width: 100px;">
                        <option value="managerName">宿管姓名:</option>
                        <option value="sex" ${searchType eq "sex"?'selected':'' }>性别:</option>
                        <option value="dormBuildId" ${searchType eq "dormBuildId"?'selected':'' }>宿舍楼栋:</option>
					</select>
					&nbsp;<input id="s_dormManagerText" name="s_dormManagerText" type="text"
                                 style="width:120px;height: 30px;" class="input-medium search-query"
                                 value="${s_dormManagerText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
    </form>
    <div>
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>电话</th>
                <th>宿舍楼</th>
                <th>操作</th>
            </tr>
            <c:forEach varStatus="i" var="dormManager" items="${dormManagerList }">
                <tr>
                    <td>${dormManager.dormManId }</td>
                    <td>${dormManager.managerName }</td>
                    <td>${dormManager.sex }</td>
                    <td>${dormManager.tel }</td>
                    <td>${dormManager.dormBuildName==null?"无":dormManager.dormBuildName }</td>
                    <td>
                        <button class="btn btn-mini btn-info" type="button"
                                onclick="javascript:location.href='/dormManSavePage?site=dormManagerSave&dormManId=${dormManager.dormManId}'">
                            修改
                        </button>&nbsp;
                        <button class="btn btn-mini btn-danger" type="button"
                                onclick="dormManagerDelete(${dormManager.dormManId})">删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p id="error" style="color: red">${error }</p>
        <%session.removeAttribute("error");%>
    </div>
    <div class="pagination pagination-centered">
        <ul>
            ${pageCode}
        </ul>
    </div>
</div>