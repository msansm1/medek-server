
angular.module('medekApp.controllers').controller('BookController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'BookService',
'BookTypeService',
'CollectionService',
'EditorService',
'LangService',
'StoryGenreService',
function($scope, $rootScope, $stateParams, $location, BookService, BookTypeService,
		CollectionService, EditorService, LangService, StoryGenreService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.bookId) != 'undefined') {
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
    } else {
    	$scope.book = { id: null,
    					title: '',
    					author: '',
    					authorId: null,
    					editor: '',
    					editorId: null,
    					collection: '',
    					collectionId: null,
    					cover: '',
    					description: '',
    					publicationDate: null,
    					genre: '',
    					genreId: null,
    					type: '',
    					typeId: null,
    					lang: '',
    					langId: null,
    					series: '',
    					bookNb: null,
    					isSerieDone: false,
    	};
    }
    
    $scope.types = [ BookTypeService.booktypes()
                        .then(
	                          function(response) {
	                              console.log("Book types : "+response.data);
	                              $scope.types = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.collections = [ CollectionService.collections()
                        .then(
	                          function(response) {
	                              console.log("Collections : "+response.data);
	                              $scope.collections = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];
    
    $scope.editors = [ EditorService.editors()
                        .then(
	                          function(response) {
	                              console.log("Editors : "+response.data);
	                              $scope.editors = response.data;
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
    
    $scope.editBook = function(id) {
    	$location.path('/books/edit/'+id);
        $location.replace();
    };
    
    $scope.saveBook = function() {
    	BookService.saveBook($scope.book);
    };
    
} ]);