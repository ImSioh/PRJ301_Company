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
                        <select name="ddlCategory">
                            <c:forEach items="${category}" var="c">
                                <c:if test="${c.getCategoryID()==p.getCategoryID()}">
                                    <option value="${c.getCategoryID()}" selected>${c.getCategoryName()}</option>
                                </c:if>
                                <c:if test="${c.getCategoryID()!=p.getCategoryID()}">
                                    <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                </c:if>
                            </c:forEach>
                            <input type="submit" value="Filter">
                            </form>
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

                                    <c:if test="${pNew != null}" var="pN">
                                        <tr>
                                            <td><a href="detail?pid=${pN.getProductID()}">#${p.getProductID()}</a></td>
                                            <td>${pN.getProductName()}</td>
                                            <td>${pN.getUnitPrice()}</td>
                                            <td>${pN.getQuantityPerUnit()}</td>
                                            <td>${pN.getUnitsInStock()}</td>
                                            <c:forEach items="${category}" var="c">
                                                <c:if test="${c.getCategoryID()==pN.getCategoryID()}">
                                                    <td> ${c.getCategoryName()} </td>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${pN.isDiscontinued()}">
                                                <td>Yes</td>
                                            </c:if>
                                            <c:if test="${!pN.isDiscontinued()}">
                                                <td>No</td>
                                            </c:if>
                                            <td>
                                                <a href="product-edit?id=${pN.getProductID()}">Edit</a> &nbsp; | &nbsp; 
                                                <a href="product-delete?id=${pN.getProductID()}">Delete</a>
                                            </td>
                                        </tr>
                                    </c:if>
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
                                            <c:if test="${p.isDiscontinued()}">
                                                <td>Yes</td>
                                            </c:if>
                                            <c:if test="${!p.isDiscontinued()}">
                                                <td>No</td>
                                            </c:if>
                                            <td>
                                                <a href="product-edit?id=${p.getProductID()}">Edit</a> &nbsp; | &nbsp; 
                                                <a href="product-delete?id=${p.getProductID()}">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div id="paging">
                                <div class="pagination">
                                    <c:if test="${numberOfPage > 1}">
                                        <c:if test="${page!=1}">
                                            <a href="product-list?page=${page-1}">&laquo;</a>
                                        </c:if>
                                        <c:forEach var = "i" begin = "1" end = "${numberOfPage}">
                                            <c:choose>
                                                <c:when test="${i==page}">
                                                    <a href="product-list?page=${i}" class="active">${i}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="product-list?page=${i}">${i}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${page!=numberOfPage}">
                                            <a href="product-list?page=${page+1}">&raquo;</a>
                                        </c:if> 
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                            <%@include file="template/footer2.jsp" %>