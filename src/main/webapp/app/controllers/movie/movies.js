
angular.module('medekApp.controllers').controller('MoviesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'MovieService',
function($scope, $rootScope, $stateParams, $location, $modal, MovieService) {
    this.userLogin = $rootScope.user.login;
    $scope.filter = "";
    $scope.listLarge = true;
    
    $scope.getMovies = function() {
        $scope.listLarge = true;
    	if ($location.path() === '/movies/me') {
            MovieService.userMovielist(0, 50, 'm.id', 'asc', $rootScope.user.id).then(
               function(response) {
                   console.log("Movies : "+response.data);
                   $scope.movies = response.data;
               }, function(reason) {
                   alert('FAILED !!!');
               });
    	} else {
    		MovieService.movielist(0, 50, 'id', 'asc').then(
	           function(response) {
	               console.log("Movies : "+response.data);
	               $scope.movies = response.data;
	           }, function(reason) {
	               alert('FAILED !!!');
	           });
    	}
    };
    
    $scope.movies = [ $scope.getMovies() ];
    
    $scope.openMovie = function(id) {
    	var index = 0;
    	if ($location.path().indexOf('/me/') > -1) {
     	   index = 10;
        } else {
     	   index = 11;
        }
        $scope.listLarge = false;
    	$location.path($location.path().substring(0,index)+'/view/'+id);
        $location.replace();
    };
    
    $scope.createMovie = function() {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/movie/addmovie.html',
            controller: 'AddMovieController',
            scope: $scope
          });
    };
    
    $scope.searchMovies = function() {
        console.log('search criteria : '+$scope.filter);
    };

} ]);

angular.module('medekApp.controllers').controller('AddMovieController',[
'$scope',
'$rootScope',
'$modalInstance',
'MovieService',
'StoryGenreService',
'SupportService',
'LangService',
function ($scope, $rootScope, $modalInstance, MovieService, StoryGenreService,
		SupportService, LangService) {
	$scope.movie = { id: null,
			title: '',
			description: '',
			releaseDate: null,
			cover: '',
			support: '',
			supportId: null,
			genre: '',
			genreId: null,
			length: null,
			isCollector: false,
			realisator: '',
			realisatorId: null,
			producer: '',
			producerId: null,
			scenarist: '',
			scenaristId: null,
			langs: [],
			subtitles: []
	};

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

    $scope.artists = [ MovieService.artists()
                               .then(
		                          function(response) {
		                              console.log("Artists : "+response.data);
		                              $scope.artists = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
    
    $scope.saveMovie = function() {
    	MovieService.saveMovie($scope.movie).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Movie saved !'});
                	$scope.getMovies();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save movie failed'});
                });
    };

    $scope.ok = function () {
    	MovieService.addMovieToCollection($scope.mymovie).then (
                function(response) {
                	$scope.saveMovie();
                	$scope.getMovies();
                    alert('Add successful');
                    $modalInstance.close('success');
                }, function(reason) {
                    alert('Add failed');
                    $modalInstance.close('fail');
                });
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);