<th:block th:fragment="banner" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">


	<section class="popular-products">
		<div class="container">
			<div class="new-product-area">
				<div th:each="item : ${displays}" class="">
					<input class="displayid" type="hidden" th:value="${item.id}" />
					<div class="row">
						<div class="col-md-12">
							<div class="title text-center">
								<h4 th:text="${item.displayName}"></h4>
							</div>
						</div>
					</div>

 					<div th:id=" 'position-' + ${item.id}" class="row">
					<!-- display list product base on call ajax loadProduct()	
					will return ve _list-home-products.jsp-->
 					</div> 
				</div>
			</div>
		</div>
	</section>






	<a href="#" class="scrollup" style="display: none;"><i
		class="ion-ios-arrow-up"></i></a>
	<script type="text/javascript">
		$(document).ready(function() {
			var ar = $(".displayid").toArray();

			for (i = 0; i < ar.length; i++) {
				var disid = ar[i].value;
				console.log(disid);

				loadProduct(disid);
			}
		})
	</script>

	<script>
		// load data display position id
		function loadProduct(id) {
			$(document).ready(function() {
				$.ajax({
					type : "GET",
					url : "/home/display_home_binding",
					data : {
						id : id,
					},
					success : function(response) {
						$("#position-" + id).html(response);
					},
					error : function(response) {
						console.log("Return error list product : " + response);
					}
				});
			});
		}
	</script>

</th:block>
