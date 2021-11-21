<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>All Products page</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light mt-5 d-flex justify-content-center" style="background-color: #e3f2fd;">
            <h3 class="nav-item p-2 center-block"><a class="nav-link text-dark" href="products">All Products</a></h3>
            <h3 class="nav-item p-2 center-block"><a class="nav-link text-dark" href="product">Add Product</a></h3>
    </nav>
</div>
<div class="container mt-5">
    <div class="container-fluid p-0 mt-2">
        <div class="row">
            <div class="col-xl-12">
                <div class="card">
                    <#if message?has_content>
                        <h2 class="m-3">${message}</h2>
                    <#else>
                        <div class="card-body">
                            <table class="table table-striped" style="width:100%">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Date</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list products as product>
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                        <td>${product.date}</td>
                                        <td>
                                            <span class="badge bg-danger">
                                                <form class="mb-0" action="/delete" method="POST">
                                                    <input hidden="text" value=${product.id} name="id"/>
                                                    <input class="border border-danger bg-danger" type="submit"
                                                           value="DELETE"/>
                                                </form>
                                            </span>
                                        </td>
                                        <td>
                                            <span class="badge bg-warning p-1">
                                                <a href="/update?id=${product.id}"
                                                   class="text-decoration-none text-reset m-1">UPDATE</a>
                                            </span>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>