<?php

namespace App\Http\Controllers;

use App\Models\Card;
use App\Models\CardSerie;
use App\Models\Serie;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Redirect;
use Inertia\Inertia;

class ContentController extends Controller
{
    public function index()
    {

        $all_series = CardSerie::orderBy('serie_name')->get();     //On récupère nos series déja existente

        return Inertia::render('Create/Create', [
            'all_series' => $all_series
        ]);
    }

    public function store_serie(Request $request)
    {

        CardSerie::create($request->all());
        return back();

    }

    public function store_card(Request $request)
    {

        Card::create($request->all());

        return back();

    }

}
