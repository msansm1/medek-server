
angular.module('medekApp.controllers').controller('MovieController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'MovieService',
function($scope, $rootScope, $stateParams, $location, MovieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.movie = [ MovieService.movie($stateParams.movieId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("Movie : "+response.data);
		                              $scope.movie = response.data;
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.update = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);