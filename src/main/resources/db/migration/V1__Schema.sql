CREATE TABLE Reviews (
  Id                     INTEGER PRIMARY KEY,
  ProductId              TEXT,
  UserId                 TEXT,
  ProfileName            TEXT,
  HelpfulnessNumerator   INTEGER,
  HelpfulnessDenominator INTEGER,
  Score                  INTEGER,
  Time                   INTEGER,
  Summary                TEXT,
  Text                   TEXT
);

CREATE INDEX ProductUserIdIndex
  ON Reviews USING BTREE (ProductId, UserId);

CREATE INDEX chem_text_fts_index
  ON Reviews
  USING GIN (to_tsvector('simple', Text));