<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@include file="template/header2.jsp" %>

<div id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">
                    <b>Filter by Catetory:</b>
                    <form>
                        <input type="hidden" name="txtSearch" value="${param.txtSearch}">
                        <select name="ddlCategory">
                            <option disabled selected value> -- select an option -- </option>
                            <c:forEach items="${category}" var="c">
                                <c:if test="${c.getCategoryID()==param.ddlCategory}">
                                    <option value="${c.getCategoryID()}" selected>${c.getCategoryName()}</option>
                                </c:if>
                                <c:if test="${c.getCategoryID()!=param.ddlCategory}">
                                    <option value="${c.getCategoryID()}" >${c.getCategoryName()}</option>
                                </c:if>

                            </c:forEach>
                        </select>
                        <input type="submit" value="Filter">
                    </form>
                    <a href="product-list?txtSearch=${param.txtSearch}">Reset</a>

                </div>
                <div id="product-title-2" style="width: 55%;">
                    <form>
                        <input type="text" name="txtSearch" placeholder="Enter product name to search"/>
                        <input type="submit" value="Search"/>
                    </form>
                </div>
                <div id="product-title-3" style="width: 20%;">
                    <a href="product-create">Create a new Product</a>
                    <form action="">
                        <label for="upload-file">Import .xls or .xlsx file</label>
                        <input type="file" name="file" id="upload-file" />
                    </form>
                </div>

            </div>
            <!--            <div>
            <c:if test="${param.txtSearch!=null}">
                Search Result for: ${param.txtSearch}
            </c:if>
        </div>-->

            <div id="order-table-admin">
                <table id="orders">
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>UnitPrice</th>
                        <th>Unit</th>
                        <th>UnitsInStock</th>
                        <th>Category</th>
                        <th>Discontinued</th>
                        <th> </th>
                    </tr>
                    <c:forEach items="${product}" var="p">
                        <tr>
                            <td><a href="detail?pid=${p.getProductID()}">#${p.getProductID()}</a></td>
                            <td>${p.getProductName()}</td>
                            <td>${p.getUnitPrice()}</td>
                            <td>${p.getQuantityPerUnit()}</td>
                            <td>${p.getUnitsInStock()}</td>
                            <c:forEach items="${category}" var="c">
                                <c:if test="${c.getCategoryID()==p.getCategoryID()}">
                                    <td> ${c.getCategoryName()} </td>
                                </c:if>
                            </c:forEach>
                            <td>${p.isDiscontinued()}</td>
                            <td>
                                <a href="product-edit?id=${p.getProductID()}">Edit</a> &nbsp; | &nbsp; 
                                <a href="product-delete?id=${p.getProductID()}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            ${numberOfPage}
            ${page}
            
            <%--<c:if test="${not empty numberOfPage}">--%>
                <div id="paging">
                    <div class="pagination">
                        
                    <c:url value = "product-list" var = "paging_url">
                        <c:param name = "txtSearch" value="${param.txtSearch}"/>
                        <c:param name = "ddlCategory" value="${param.ddlCategory}"/>
                    </c:url>
                        
                    <c:if test="${numberOfPage > 1}">
                        <a href="${paging_url}&page=${page-1}">&laquo;</a>
                        <c:forEach var = "i" begin = "1" end = "${numberOfPage}">
                            <c:choose>
                                <c:when test="${i==page}">
                                    <a href="${paging_url}&page=${i}" class="active">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${paging_url}&page=${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <a href="${paging_url}&page=${page+1}">&raquo;</a>
                    </c:if>
                    </div>
                </div>
            <%--</c:if>--%>

        </div>
    </div>
</div>


<%@include file="template/footer2.jsp" %>
