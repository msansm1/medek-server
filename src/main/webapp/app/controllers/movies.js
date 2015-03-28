
angular.module('medekApp.controllers').controller('MoviesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'MovieService',
function($scope, $rootScope, $stateParams, $location, MovieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.movies = [ MovieService.movies()
                               .then(
		                          function(response) {
		                              console.log("Movies : "+response.data);
		                              $scope.movies = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.createMovie = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);