<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Update Product Page</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light mt-5 d-flex justify-content-center" style="background-color: #e3f2fd;">
        <h3 class="nav-item p-2 center-block"><a class="nav-link text-dark" href="products">All Products</a></h3>
        <h3 class="nav-item p-2 center-block"><a class="nav-link text-dark" href="product">Add Product</a></h3>
    </nav>
</div>
<div class="container mt-5">
    <form action="/update" method="POST">
        <input hidden="text" value=${product.id} name="id"/>
        <div class="d-flex flex-column align-items-center">
            <div class="row w-100 p-2 d-flex flex-row">
                <h5 class="col-4">Product name:</h5>
                <input class="col-4" type="text" value=${product.name} name="name"/>
            </div>
            <div class="row w-100 p-2 d-flex flex-row">
                <h5 class="col-4">Product price:</h5>
                <input class="col-4" type="number" step="0.01" value=${product.price} name="price"/>
            </div>
            <div class="row w-100 p-2 d-flex flex-row">
                <h5 class="col-4">Date: </h5>
                <input class="col-4" type="date" value=${product.date} name="date"/>
            </div>
            <div class="row w-100 p-2 d-flex flex-row justify-content-center">
                <input class="col-2 bg-success m-4" type="submit" value="UPDATE">
            </div>
        </div>
    </form>
</div>
</body>
</html>