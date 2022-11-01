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
<!--                        <select name="ddlCategory">
                            <option value="catid1">Smart Phone</option>
                            <option value="catid2">Computer</option>
                            <option value="catid3">Television</option>
                            <option value="catid4">Electronic</option>
                        </select>-->
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
                            <div id="paging">
                                <div class="pagination">
                                    <a href="#">&laquo;</a>
                                    <a href="#">1</a>
                                    <a href="#" class="active">2</a>
                                    <a href="#">3</a>
                                    <a href="#">4</a>
                                    <a href="#">5</a>
                                    <a href="#">6</a>
                                    <a href="#">&raquo;</a>
                                </div>
                            </div>
                            </div>
                            </div>
                            </div>


                            <%@include file="template/footer2.jsp" %>