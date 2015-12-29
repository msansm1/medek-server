
angular.module('medekApp.controllers').controller('MovieController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'$upload',
'MovieService',
'StoryGenreService',
'SupportService',
'LangService',
function($scope, $rootScope, $stateParams, $location, $modal, $upload, MovieService, StoryGenreService,
		SupportService, LangService) {
    this.userLogin = $rootScope.user.login;

    if (typeof ($stateParams.movieId) != 'undefined') {
    	$scope.movie = [ MovieService.movie($stateParams.movieId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("Movie : "+response.data);
		                              $scope.movie = response.data;
		                              if ($location.path().indexOf('/me/') > -1) {
		                           	   $scope.movie.myview = true;
		                              } else {
		                           	   $scope.movie.myview = false;
		                              }
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    } else {
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
    	$('.mainlist').addClass('col-md-4');
    	$('.mainlist').removeClass('col-md-8');
    	$('.itempanel').addClass('col-md-6');
	}

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
    
    $scope.editMovie = function(id) {
    	$location.path('/movies/me/edit/'+id);
        $location.replace();
    }
    
    $scope.saveMovie = function() {
    	MovieService.saveMovie($scope.movie).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Movie saved !'});
                	$scope.$parent.getMovies();
                	$location.path('/movies/edit/'+response.data.id);
                    $location.replace();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save movie failed'});
                });
    };
    
    // cover upload
    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url: 'services/movies/'+$scope.movie.id+'/coverupload',
            method: 'POST',
            headers: {'securtoken': $rootScope.user.token},
            //withCredentials: true,
            data: {id: $scope.movie.id},
            file: file // or list of files ($files) for html5 only
          }).progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total, 10));
          }).success(function(data, status, headers, config) {
            console.log('SUCCESS !! '+data);
          });
        }
    };
    
    $scope.addMovieToMyCollec = function(movie) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/movie/addtocollec.html',
            controller: 'MovieCollecController',
            resolve: {
                movie: function () {
                    return movie;
                }
              }
          });
    };

} ]);

angular.module('medekApp.controllers').controller('MovieCollecController',[
'$scope',
'$rootScope',
'$modalInstance',
'MovieService',
'movie',
function ($scope, $rootScope, $modalInstance, MovieService, movie) {
	$scope.movie = movie;
	$scope.mymovie = {
			movieId: movie.id,
			userId: $rootScope.user.id,
			rating: 0,
			comment: ""
	};

    $scope.ok = function () {
    	MovieService.addMovieToCollection($scope.mymovie).then (
                function(response) {
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