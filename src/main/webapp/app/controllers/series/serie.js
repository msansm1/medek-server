
angular.module('medekApp.controllers').controller('SerieController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'SerieService',
'StoryGenreService',
'SupportService',
'LangService',
function($scope, $rootScope, $stateParams, $location, SerieService, StoryGenreService,
		SupportService, LangService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.serieId) != 'undefined') {
	    $scope.serie = [ SerieService.serie($stateParams.serieId)
	                               .then(
			                          function(response) {
			                        	  $('.mainlist').addClass('col-md-4');
			                        	  $('.mainlist').removeClass('col-md-8');
			                              console.log("Serie : "+response.data);
			                              $scope.serie = response.data;
			                              $('.itempanel').addClass('col-md-6');
			                          }, function(reason) {
			                              alert('FAILED !!!');
			                          }) ];
    } else {
    	$scope.serie = { id: null,
						title: '',
						description: '',
						releaseDate: null,
						cover: '',
						support: '',
						supportId: null,
						genre: '',
						genreId: null,
						length: null,
						season: null,
						series: '',
						isSeriesDone: false,
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

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		
		$scope.opened = true;
	};
    
    $scope.editSerie = function(id) {
    	$location.path('/series/edit/'+id);
        $location.replace();
    };
    
    $scope.saveSerie = function() {
    	SerieService.saveSerie($scope.serie);
    };

} ]);