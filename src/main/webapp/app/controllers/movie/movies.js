
angular.module('medekApp.controllers').controller('MoviesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'MovieService',
function($scope, $rootScope, $stateParams, $location, MovieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getMovies = function() {
    	MovieService.movies().then(
           function(response) {
               console.log("Movies : "+response.data);
               $scope.movies = response.data;
           }, function(reason) {
               alert('FAILED !!!');
           });
    };
    
    $scope.movies = [ $scope.getMovies() ];
    
    $scope.openMovie = function(id) {
    	$location.path('/movies/'+id);
        $location.replace();
    }
    
    $scope.createMovie = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);