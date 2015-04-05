
angular.module('medekApp.controllers').controller('BookController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'BookService',
function($scope, $rootScope, $stateParams, $location, BookService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.book = [ BookService.book($stateParams.bookId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("Book : "+response.data);
		                              $scope.book = response.data;
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.update = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);