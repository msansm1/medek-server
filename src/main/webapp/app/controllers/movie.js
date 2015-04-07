
angular.module('medekApp.controllers').controller('MovieController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'MovieService',
'StoryGenreService',
'SupportService',
'LangService',
function($scope, $rootScope, $stateParams, $location, MovieService, StoryGenreService,
		SupportService, LangService) {
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

    $scope.supports = [ SupportService.supports()
                               .then(
		                          function(response) {
		                              console.log("Supports : "+response.data);
		                              $scope.supports = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.langs = [ LangService.langs()
                        .then(
	                          function(response) {
	                              console.log("Langs : "+response.data);
	                              $scope.langs = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.genres = [ StoryGenreService.storygenres()
                        .then(
	                          function(response) {
	                              console.log("Story genres : "+response.data);
	                              $scope.genres = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		
		$scope.opened = true;
	};
    
    $scope.editMovie = function(id) {
    	$location.path('/movies/edit/'+id);
        $location.replace();
    }
    
    $scope.updateMovie = function() {
    	MovieService.saveMovie($scope.movie);
    };

} ]);