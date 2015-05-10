
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

    if (typeof ($stateParams.movieId) != 'undefined') {
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
    	$location.path('/movies/edit/'+id);
        $location.replace();
    }
    
    $scope.saveMovie = function() {
    	MovieService.saveMovie($scope.movie);
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

} ]);