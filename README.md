wilco-api
========================

Reference guide to deal with the WILCO API and how to design your application with it


The project contains also a set of pieces of code to interface it at a higher level than raw JSON
Currently JAVA only implementation

ROADMAP
========================
would be great to build standardized API like (Play module?)

````
DRY routes definition
For each resource, the URI and methods for controllers are in unified form, so maybe we can define a common routes set for all ResourceControllers
GET /:resourceCollectionName ResourceController.list()
POST /:resourceCollectionName ResourceController.create()
PUT /:resourceCollectionName ResourceController.replace()
PATCH /:resourceCollectionName ResourceController.edit()
DELETE /:resourceCollectionName ResourceController.delete()
GET /:resourceCollectionName/:resourceId ResourceController.get(resourceId:Long)
PUT /:resourceCollectionName/:resourceId ResourceController.replace(resourceId:Long)
PATCH /:resourceCollectionName/:resourceId ResourceController.edit(resourceId:Long)
DELETE /:resourceCollectionName/:resourceId ResourceController.delete(resourceId:Long)
API versioning
add a prefix /v:versionNumber (versionNumber:Int) to all URIs
Example: /v1/customers/1
Built-in query support for GET /:resourceCollectionName
fields - return partial data rather than the whole resource
Example
Request: GET /customers?fields=name,id
Response: [{"id": 1, "name": "Foo"}, {{"id": 2, "name": "Bar"}}]
expand - fetch related data
Example
Request: GET /customers?expand=orders,payments
Response: [{"id": 1, "name": "Foo", "orders": [{}, {}, {}], "payments": [{}, {}, {}]}, {{"id": 2, "name": "Bar", "orders": [{}, {}, {}], "payments": [{}, {}, {}}]
pagination
Example
Request: GET /customers?offset=0&limit=20
Response: the first 20 customers
orderby
Example
Request: GET /customers?orderby=name asc, id desc
Response: all customer in the give order
Built-in HASTOAS support
For each response, automatically add "href"
Example
Request: GET/ customers/1
Response: {"href": "http://yourdomain/customers/1", "id": 1, name: "foo"}
For collection response, automatically add pagination links
Example
Request: GET/ customers?offset=0&limit=20
Response: {"href": "http://yourdomain/customers?offset=0&limit=20", "previous": null, "next": "http://yourdomain/customers?offset=20&limit=20", "first": "http://yourdomain/customers?offset=0&limit=20", "last": "http://yourdomain/customers?offset=120&limit=20", item: [{"href": "http://yourdomain/customers/1", "id": 1, name: "foo"}, "href": "http://yourdomain/customers/2", "id": 2, name: "bar"] ... }
For relational data, automatically add "href"
Example
Request: GET /customers/1
Response: {"href": "http://yourdomain/customers/1", "id": 1, name: "foo", "orders": {"href": "http://yourdomain/customers/1/orders"}, "payments" : {"href": "http://yourdomain/customers/1/payments"}}
etc
The purpose for this module/plugin is to
make it more DRY when building Restful APIs using Play! Framework
provide a Restful practice, thus the developers don't need to make the decisions such as
where the version number of the API should be placed
which parameter should be used for pagination
````

