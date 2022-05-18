<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Card extends Model
{

    use HasFactory;

    protected $fillable = ['serie_id','card_name','first_forbidden_word','second_forbidden_word','third_forbidden_word','fourth_forbidden_word'];

}
